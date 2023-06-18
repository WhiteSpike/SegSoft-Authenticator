package accessControl;

import accounts.Account;
import exceptions.AccessControllerDeniedException;

public interface AccessController {

    /**
     * Creates a new role with given identifier
     * @param roleId Identifier of the new role
     * @return Role object with given identifier
     */
    Role newRole(String roleId);

    /**
     * Changes the role of a given account into the given role
     * @param user Account which we want to change the role of
     * @param role The new role which is gonna be associated to the previous role
     */
    void setRole(Account user, Role role);

    /**
     * Returns the list of roles given to the account
     * @param user Account which we want to know the roles of
     * @return List of roles given to the account
     */
    Role[] getRoles(Account user);

    /**
     * Grants permission to a given role to do a given operation in a given resource
     * @param role Role which gets granted access to a given operation in a given resource
     * @param res Resource where the role can execute the operation
     * @param op Operation which the role will be granted to execute
     */
    void grantPermission(Role role, Resource res, Operation op);

    /**
     * Removes permission from a given role to a given operation in a given resource
     * @param role Role which gets removed access to a given operation in a given resource
     * @param res Resource where the role can execute the operation
     * @param op Operation which the role will be granted to execute
     */
    void revokePermission(Role role, Resource res, Operation op);

    /**
     * @param role Role which we want the capability from
     * @return a capability associated wtih given role
     */
    Capability makeKey(Role role);

    /**
     * Checks if the given capability has access to do a given operation in a given resource
     * @param cap Capability which we want to check if they have access to execute given operation
     * @param res Resource where the role can execute the operation
     * @param op Operation which the role will be granted to execute
     */
    void checkPermission(Capability cap, Resource res, Operation op) throws AccessControllerDeniedException;
}
