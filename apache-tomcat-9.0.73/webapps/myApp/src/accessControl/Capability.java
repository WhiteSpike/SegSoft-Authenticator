package accessControl;

import java.util.List;

public interface Capability {

    List<Permission> getAuthorizedOperations();
}
