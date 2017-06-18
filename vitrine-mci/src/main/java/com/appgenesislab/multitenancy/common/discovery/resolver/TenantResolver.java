package com.appgenesislab.multitenancy.common.discovery.resolver;


import com.appgenesislab.multitenancy.common.discovery.mapper.UserToTenantMapper;
import com.appgenesislab.multitenancy.common.discovery.model.TenantData;
import java.util.function.BiFunction;

public interface TenantResolver extends BiFunction<UserResolver,UserToTenantMapper,TenantData>
{

}
