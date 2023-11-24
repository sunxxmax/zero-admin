## Spring Security
- Spring Security 是基于 Spring AOP 和 Servlet 过滤器的安全框架，提供全面的安全性解决方案。
- Spring Security核心功能包括用户认证（Authentication）、用户授权（Authorization）和攻击防护3个部分：

> 用户认证指的是验证某个用户是否为系统中的合法主体，也就是说用户能否访问该系统。
> 
> 用户认证一般要求用户提供用户名和密码。系统通过校验用户名和密码来完成认证过程

> 用户授权指的是验证某个用户是否有权限执行某个操作。
> 
> 在一个系统中，不同用户所具有的权限是不同的。
> 
> 比如对一个文件来说，有的用户只能进行读取，而有的用户可以进行修改。
>
> 一般来说，系统会为不同的用户分配不同的角色，而每个角色则对应一系列的权限

> 攻击防护即防止伪造身份
> 
> Spring security大量使用了责任链和委托的代码设计风格，过滤器负责对请求进行安全校验和设置，某个过滤器涉及认证或授权时，认证/授权具体实现委派给认证管理器和授权管理器，过滤器不负责具体实现

## 常见问题

1. 分页查询

2. JPA 审计

## 问题

1. 为什么要使用Spring Security？

    Spring Security 是一个强大且广泛使用的身份验证和访问控制框架，它为基于 Java 的企业应用程序提供了全面的安全性解决方案。以下是一些使用 Spring Security 的主要原因：
    
   1. **身份验证（Authentication）：** Spring Security 提供了灵活的身份验证机制，可轻松集成到各种身份验证提供者，如数据库、LDAP、OAuth 等。它支持多种身份验证方式，包括用户名密码、证书、记住我等。
    
   2. **授权（Authorization）：** Spring Security 支持基于角色和权限的访问控制。通过定义用户的角色和权限，可以限制用户对系统资源的访问。这有助于确保只有授权的用户能够执行特定的操作。
    
   3. **Web 安全性：** Spring Security 提供了用于保护 Web 应用程序的工具和过滤器，例如防止跨站请求伪造（CSRF）攻击、点击劫持等。它能够轻松集成到 Spring MVC 和其他 Web 框架中。
    
   4. **单点登录（SSO）：** Spring Security 支持单点登录，使用户能够在多个相关联的应用程序中使用相同的凭证进行身份验证，提高用户体验和减少密码管理的负担。
    
   5. **会话管理：** Spring Security 允许开发人员对用户会话进行管理，包括限制同时活动的会话数量、设置会话超时等。这有助于提高系统的安全性和性能。
    
   6. **密码加密：** Spring Security 提供了强大的密码加密和哈希算法，确保用户密码的安全性。它支持多种密码存储方案，包括BCrypt、SHA等。
    
   7. **集成性：** Spring Security 可以轻松集成到 Spring 框架中，与其他 Spring 模块协同工作。这使得在 Spring 应用程序中实现端到端的安全性变得更加简单。
    
   8. **可扩展性：** Spring Security 是一个可扩展的框架，允许开发人员定义自定义的安全性策略、身份验证提供者和访问控制规则。
    
   总的来说，使用 Spring Security 可以为应用程序提供全面的安全性保障，保护系统免受常见的安全威胁。无论是企业级应用程序还是小型项目，Spring Security 都是一个值得考虑的强大工具。

2. Spring Security 与 Shiro 的区别
3. Spring Security 与 OAuth2 的区别
4. Spring Security 与 JWT 的区别
5. Spring Security 与 Spring Session 的区别
6. Spring Security 与 Spring Cloud Security 的区别
7. Spring Security 与 Spring Social 的区别
8. Spring Security 与 Spring LDAP 的区别
9. Spring Security 与 Spring Kerberos 的区别
10. Spring Security 与 Spring CAS 的区别
11. Spring Security 与 Spring SAML 的区别
