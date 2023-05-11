package accessControl;

public class CapabilityClass implements Capability {
    private final Role role;

    /**
     * Default constructor of the Capability
     * @param role Role associated with the capability
     */
    // TODO Crypto this to be unforgeable and authenticated
    // TODO Must have additional information to know which operations on which resources this role has access to
    // TODO This is basically a token, we can use the encrypt method for password on this
    public CapabilityClass(Role role) {
        this.role = role;
    }

    @Override
    public Role getRole(){
        return this.role;
    }
}
