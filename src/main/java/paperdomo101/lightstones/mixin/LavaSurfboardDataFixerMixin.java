package paperdomo101.lightstones.mixin;

import com.mojang.datafixers.DSL;
import com.mojang.datafixers.types.Type;
import net.minecraft.entity.EntityType;
import net.minecraft.util.Util;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;


@Mixin(EntityType.Builder.class)
public abstract class LavaSurfboardDataFixerMixin {
    @Redirect(
            method = "build",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/util/Util;getChoiceType(Lcom/mojang/datafixers/DSL$TypeReference;Ljava/lang/String;)Lcom/mojang/datafixers/types/Type;"
            )
    )
    private Type<?> lightstones$skipSurfboardDataFixer(DSL.TypeReference typeReference, String id) {
        if ("lava_surfboard".equals(id)) { return null; }
        return Util.getChoiceType(typeReference, id);
    }
}
