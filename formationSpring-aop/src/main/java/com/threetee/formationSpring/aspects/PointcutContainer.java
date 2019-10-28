package com.threetee.formationSpring.aspects;

import org.aspectj.lang.annotation.Pointcut;

import com.threetee.formationSpring.services.UserService;


public class PointcutContainer {

    @Pointcut("execution( * com.threetee.formationSpring.dao.*.*UserRepo+.update*(Long, String))")
    public void repoUpdate() {
    }

    @Pointcut("execution (* com.threetee.formationSpring.*Service+.update*(..)) && args(id,pass) && target (service)")
    public void serviceUpdate(UserService service, Long id, String pass) {
    }

    @Pointcut("execution( * com.threetee.formationSpring.*.*UserRepo+.update*(..))")
    public void proxyBubu() {
    }
}
