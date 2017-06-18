package com.appgenesislab.multitenancy.common.discovery.mapper;

@FunctionalInterface
public interface UserToTenantMapper<T,R>
{
     R map(T userData);
}
