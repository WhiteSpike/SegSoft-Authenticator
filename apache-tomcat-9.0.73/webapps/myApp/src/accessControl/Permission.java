package accessControl;

public class Permission {
    private Resource resource;
    private Operation operation;

    public Permission(Resource resource, Operation operation)
    {
        this.resource = resource;
        this.operation = operation;
    }

    public Resource getResource()
    {
        return this.resource;
    }

    public Operation getOperation()
    {
        return this.operation;
    }

    @Override
    public boolean equals(Object other)
    {
        if (this == other)
            return true;

        if (!(other instanceof Permission perm)) return false;

        return (this.resource == perm.getResource() && this.operation == perm.getOperation());
    }
}
