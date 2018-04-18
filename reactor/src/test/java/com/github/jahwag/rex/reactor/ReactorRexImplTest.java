package com.github.jahwag.rex.reactor;

import com.github.jahwag.rex.command.Command;
import com.github.jahwag.rex.reaction.Reaction;
import lombok.Builder;
import lombok.Value;
import lombok.experimental.Accessors;
import org.junit.Test;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static org.junit.Assert.assertNotNull;

public class ReactorRexImplTest {

    private final ReactorRex rex = ReactorRex.create();

    @Test
    public void broadcast() {
        rex.subscribe(new ListAccountsReaction(), ListAccounts.class);

        Flux<Account> results = rex.publish(ListAccounts.builder()
                                                        .minimumBalance(1000)
                                                        .build());

        assertNotNull(results);

        StepVerifier.create(results)
                    .expectNext(Account.of("Bob", 1000))
                    .expectNext(Account.of("Sara", 1500))
                    .verifyComplete();
    }

    @Value
    @Builder
    @Accessors(fluent = true)
    static class ListAccounts implements Command<Account> {

        private final int minimumBalance;

    }

    static class ListAccountsReaction implements Reaction<ListAccounts, Account> {

        private final List<Account> repository = Arrays.asList(
                Account.of("Lenny", 750),
                Account.of("Bob", 1000),
                Account.of("Sara", 1500));

        private Predicate<Account> accept(int minimumBalance) {
            return account -> account.balance >= minimumBalance;
        }

        @Override
        public Publisher<Account> apply(ListAccounts command) {
            return Flux.fromStream(repository.stream()
                                             .filter(accept(command.minimumBalance())));
        }
    }

    @Value(staticConstructor = "of")
    @Accessors(fluent = true)
    static class Account {

        private final String holder;

        private final int balance;
    }
}
