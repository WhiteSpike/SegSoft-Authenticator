package accessControl;

import accounts.Account;

public class AccessControllerClass implements AccessController{

    @Override
    public Role newRole(String roleId) {
        // Insert role into database/file "roles.txt"

        return new RoleClass(roleId);
    }

    @Override
    public void setRole(Account user, Role role) {
        // Associate role with account into database/file "roleAccounts.txt"
    }

    @Override
    public Role[] getRoles(Account user) {
        // Get all associations of roles with given account from database/file "roleAccounts.txt"
        return null;
    }

    @Override
    public void grantPermission(Role role, Resource res, Operation op) {
        // Inserts permission to a given role, resource and operation into a database/file "rolePermissions.txt"
    }

    @Override
    public void revokePermission(Role role, Resource res, Operation op) {
        // Removes permission from a given role, resource and operation from the database/file "rolePermissions.txt"
    }

    @Override
    public Capability makeKey(Role role) {
        // Memory return, does not need to go to database/file
        return new CapabilityClass(role);
    }

    @Override
    public void checkPermission(Capability cap, Resource res, Operation op) {
        // Gets the role from the Capability and checks if it has access to a given resource and operation from the database/file "rolePermissions.txt"
    }
}
