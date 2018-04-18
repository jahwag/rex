package com.github.jahwag.rex.cdi.reaction;

import com.github.jahwag.rex.annotations.AutoSubscribe;
import com.github.jahwag.rex.cdi.command.RevokeRole;
import com.github.jahwag.rex.cdi.user.User;
import com.github.jahwag.rex.cdi.user.Users;
import com.github.jahwag.rex.reaction.Reaction;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

@AutoSubscribe(RevokeRole.class)
@ApplicationScoped
public class RevokeRoleReaction implements Reaction<RevokeRole, Void> {

    @Inject
    Users users;

    @Transactional
    @Override
    public Publisher<Void> apply(RevokeRole command) {
        return Mono.fromRunnable(() -> serve(command));
    }

    private void serve(RevokeRole command) {
        User user = users.findBy(command.username());
        user.setRole(command.role());

        users.save(user);
    }

}
