package team.lodestar.lodestone.systems.rendering.ghost;

import io.github.fabricators_of_create.porting_lib.model.IModelData;
import io.github.fabricators_of_create.porting_lib.model.ModelProperty;

public enum VirtualEmptyModelData implements IModelData {
    INSTANCE;

    public static boolean is(IModelData data) {
        return data == INSTANCE;
    }

    @Override
    public boolean hasProperty(ModelProperty<?> prop) {
        return false;
    }

    @Override
    public <T> T getData(ModelProperty<T> prop) {
        return null;
    }

    @Override
    public <T> T setData(ModelProperty<T> prop, T data) {
        return null;
    }
}
