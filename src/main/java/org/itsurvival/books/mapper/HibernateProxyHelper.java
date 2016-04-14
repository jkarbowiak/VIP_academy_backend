package org.itsurvival.books.mapper;

import org.hibernate.proxy.HibernateProxy;

public abstract class HibernateProxyHelper {

    // do not instantiate
    private HibernateProxyHelper() {
        throw new AssertionError();
    }

    @SuppressWarnings("unchecked")
    public static <T> T unwrap(T o) {
        if (o instanceof HibernateProxy)
            return (T) ((HibernateProxy)o).getHibernateLazyInitializer().getImplementation();
        else
            return o;
    }

}
