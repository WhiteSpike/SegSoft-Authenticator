package accessControl;

import accounts.Account;
import exceptions.AccessControllerDeniedException;

import javax.servlet.ServletContext;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.LinkedList;
import java.util.List;

public class AccessControllerClass implements AccessController{

    private String filePath;

    public AccessControllerClass(ServletContext context)
    {
        this.filePath = context.getRealPath("WEB-INF/data");
    }

    @Override
    public Role newRole(String roleId) {
        // Insert role into database/file "roles.txt"
        Role role = new RoleClass(roleId);
        writeRoleIntoFile(role);

        return role;
    }

    @Override
    public void setRole(Account user, Role role) {
        // Associate role with account into database/file "roleAccounts.txt"
        writeRoleIntoAccount(role, user);
    }

    @Override
    public Role[] getRoles(Account user) {
        // Get all associations of roles with given account from database/file "roleAccounts.txt"
        return checkRolesFromAccount(user);
    }

    @Override
    public void grantPermission(Role role, Resource res, Operation op) {
        // Inserts permission to a given role, resource and operation into a database/file "rolePermissions.txt"
        writePermissionIntoRole(role, res, op);
    }

    @Override
    public void revokePermission(Role role, Resource res, Operation op) {
        // Removes permission from a given role, resource and operation from the database/file "rolePermissions.txt"
        removePermissionFromRole(role, res, op);
    }

    @Override
    public Capability makeKey(Role role) {
        List<Permission> authorizedOperations = GetAuthorizedOperations(role);
        return new CapabilityClass(authorizedOperations);
    }

    private List<Permission> GetAuthorizedOperations(Role role) {
        try {
            List<Permission> authorizedOperations = new LinkedList<>();
            File inFile = new File(this.filePath + "/rolePermissions.txt");
            FileReader file = new FileReader(inFile);
            BufferedReader in = new BufferedReader(file);
            String line;
            while((line = in.readLine()) != null){
                String[] parameters = line.split(",");
                String roleID = parameters[0];
                String resource = parameters[1];
                String operation = parameters[2];

                if (!roleID.equals(role.getRoleID()))
                    authorizedOperations.add(new Permission(Resource.valueOf(resource), Operation.valueOf(operation)));

            }
            in.close();
            return authorizedOperations;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void checkPermission(Capability cap, Resource res, Operation op) throws AccessControllerDeniedException {
        // Gets the role from the Capability and checks if it has access to a given resource and operation from the database/file "rolePermissions.txt"
        if(!cap.getAuthorizedOperations().contains(new Permission(res, op))) throw new AccessControllerDeniedException();
    }

    private void writeRoleIntoFile(Role role) {
        try {
            FileWriter out = new FileWriter(this.filePath + "/roles.txt", true);
            out.write(role.getRoleID() + "\n");
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void removeRoleFromFile(String name) {
        try {
            File inFile = new File(this.filePath + "/roles.txt");
            File outFile = new File(this.filePath + "/temproles.txt");
            FileReader file = new FileReader(inFile);
            BufferedReader in = new BufferedReader(file);
            FileWriter out = new FileWriter(outFile, false);
            String line;
            while((line = in.readLine()) != null){
                line += '\n';
                String[] parameters = line.split(",");
                String roleName = parameters[0];

                if (!roleName.equals(name))
                    out.write(line);
            }
            in.close();
            out.close();
            outFile.renameTo(inFile);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void writeRoleIntoAccount(Role role, Account account)
    {
        try {
            FileWriter out = new FileWriter(this.filePath + "/roleAccounts.txt", true);
            out.write(account.GetAccountName() + "," + role.getRoleID() + "\n");
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void removeRoleFromAccount(String roleName, String accountName) {
        try {
            File inFile = new File(this.filePath + "/roleAccounts.txt");
            File outFile = new File(this.filePath + "/temproleAccounts.txt");
            FileReader file = new FileReader(inFile);
            BufferedReader in = new BufferedReader(file);
            FileWriter out = new FileWriter(outFile, false);
            String line;
            while((line = in.readLine()) != null){
                line += '\n';
                String[] parameters = line.split(",");
                String roleID = parameters[0];
                String accountId = parameters[1];

                if (!roleID.equals(roleName) || !accountId.equals(accountName))
                    out.write(line);
            }
            in.close();
            out.close();
            outFile.renameTo(inFile);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void writePermissionIntoRole(Role role, Resource res, Operation op) {
        try {
            FileWriter out = new FileWriter(this.filePath + "/rolePermissions.txt", true);
            out.write(role.getRoleID() + "," + res.toString() + "," + op.toString() + "\n");
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void removePermissionFromRole(Role role, Resource res, Operation op) {
        try {
            File inFile = new File(this.filePath + "/rolePermissions.txt");
            File outFile = new File(this.filePath + "/temprolePermissions.txt");
            FileReader file = new FileReader(inFile);
            BufferedReader in = new BufferedReader(file);
            FileWriter out = new FileWriter(outFile, false);
            String line;
            while((line = in.readLine()) != null){
                line += '\n';
                String[] parameters = line.split(",");
                String roleID = parameters[0];
                String resource = parameters[1];
                String operation = parameters[2];

                if (!roleID.equals(role.getRoleID()) || !resource.equals(res.toString()) || !operation.equals(op.toString()))
                    out.write(line);
            }
            in.close();
            out.close();
            outFile.renameTo(inFile);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private boolean checkPermissionInDatabase(Role role, Resource res, Operation op) {
        try {
            File inFile = new File(this.filePath + "/rolePermissions.txt");
            FileReader file = new FileReader(inFile);
            BufferedReader in = new BufferedReader(file);
            String line;
            while((line = in.readLine()) != null){
                String[] parameters = line.split(",");
                String roleID = parameters[0];
                String resource = parameters[1];
                String operation = parameters[2];

                if (roleID.equals(role.getRoleID()) && resource.equals(res.toString()) && operation.equals(op.toString()))
                    return true;
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private Role[] checkRolesFromAccount(Account user){
        List<Role> roles = new LinkedList<>();
        try {
            File inFile = new File(this.filePath + "/roleAccounts.txt");
            FileReader file = new FileReader(inFile);
            BufferedReader in = new BufferedReader(file);
            String line;
            while((line = in.readLine()) != null){
                String[] parameters = line.split(",");
                String roleID = parameters[0];
                String accountId = parameters[1];

                if (accountId.equals(user.GetAccountName()))
                    roles.add(new RoleClass(roleID));
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (Role[]) roles.toArray();
    }
}
