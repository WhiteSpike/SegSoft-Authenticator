package accessControl;

public class RoleClass implements Role {
    private final String roleId;

    public RoleClass(String roleId){
        this.roleId = roleId;
    }

    @Override
    public String getRoleID() {
        return roleId;
    }
}
