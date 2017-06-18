package com.appgenesislab.multitenancy.common.discovery.resolver;

import com.appgenesislab.multitenancy.common.discovery.model.UserData;

@FunctionalInterface
public interface UserResolver<T>
{

     UserData getUserData(T value);

}
