package accessControl;

import java.util.List;

public class CapabilityClass implements Capability {
    private final List<Permission> permissions;

    public CapabilityClass(List<Permission> permissions) {
        this.permissions = permissions;
    }

    @Override
    public List<Permission> getAuthorizedOperations() {
        return this.permissions;
    }
}
