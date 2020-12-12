package org.ikinsure.advisor;

import java.util.*;

public class Test {

    static class A {

    }

    static class B extends A {

    }

    static class C extends B {

    }

    public static void main(String[] args) {

        List<? extends A> upperWildcard = new ArrayList<>();

        List<C> cList = new ArrayList<>(List.of(new C(), new C()));

        upperWildcard = cList;
        Object a = upperWildcard.get(0);

    }
}
