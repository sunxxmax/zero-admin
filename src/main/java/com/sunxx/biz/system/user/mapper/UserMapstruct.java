package com.sunxx.biz.system.user.mapper;

import com.sunxx.biz.system.user.domain.User;
import com.sunxx.biz.system.user.bean.UserCreate;
import com.sunxx.biz.system.user.bean.UserUpdate;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapstruct {

//    /**
//     * id不需要转换
//     *
//     * @param source 源对象
//     * @param target 目标对象
//     */
//    @Mapping(target = "id", ignore = true)
//    void user2User(User source, @MappingTarget User target);

    /**
     * 转换 UserCreate -> User
     *
     * @param source 源对象
     * @return 目标对象
     */
    User userCreate2User(UserCreate source);

    /**
     * 转换 UserUpdate -> User
     *
     * @param source 源对象
     * @param target 目标对象
     */
    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "username", target = "username"),
            @Mapping(source = "password", target = "password")
    })
    void userUpdate2User(UserUpdate source, @MappingTarget User target);
}
