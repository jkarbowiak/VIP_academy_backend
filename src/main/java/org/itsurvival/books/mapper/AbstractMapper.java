package org.itsurvival.books.mapper;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractMapper<Entity, To> {

    public abstract To convertToTransportObject(Entity source);

    public abstract Entity convertToEntity(To target);

    public List<To> mapSourceCollection(Collection<Entity> collection) {
        return collection.stream().map(this::convertToTransportObject).collect(Collectors.toList());
    }

    public List<Entity> mapTargetCollection(Collection<To> collection) {
        return collection.stream().map(this::convertToEntity).collect(Collectors.toList());
    }

}
