import java.security.Permission;

public class MySecurityManager extends SecurityManager
{
@Override
     public void checkPermission(Permission perm, Object context)
     {
     }

 @Override
         public void checkPermission(Permission perm) 
                 {
                             // allow anything.
                                     }

@Override public void checkExit(int status) {
    throw new SecurityException();
      }
      }
