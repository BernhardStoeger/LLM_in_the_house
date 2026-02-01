package scead.llminthehouse.web.mapper;

import scead.llminthehouse.base.channel.web.dto.UserWebDTO;
import scead.llminthehouse.domain.businessobject.User;

public class UserMapper {
    public static UserWebDTO mapUser(User user) {
        return new UserWebDTO(user.getId(), user.getUuid(), user.getUsername(), user.getCreatedOn());
    }
}
