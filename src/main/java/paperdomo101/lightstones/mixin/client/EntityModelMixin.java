package paperdomo101.lightstones.mixin.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;

@Environment(EnvType.CLIENT)
@Mixin(EntityModel.class)
public class EntityModelMixin<T extends Entity> {

//    public boolean surfing;

//     @Inject(at = @At(value = "INVOKE_ASSIGN", target = "Lnet.minecraft.client.render.entity.model.EntityModel;"), method = "copyStateTo", cancellable = true)
//     private void copyStateTo(EntityModelMixin<T> copy) {
//         copy.surfing = this.surfing;
//     }
}
