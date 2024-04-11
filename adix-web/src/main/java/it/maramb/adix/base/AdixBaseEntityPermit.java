package it.maramb.adix.base;

import it.maramb.adix.entity.User;

public interface AdixBaseEntityPermit {

    Boolean isEnabled();

    Boolean isEnabledFor(User user);
}
