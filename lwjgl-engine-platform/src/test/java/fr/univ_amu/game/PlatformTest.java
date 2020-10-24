package fr.univ_amu.game;

import fr.univ_amu.game.core.GraphicPlatform;
import fr.univ_amu.game.core.Platform;
import fr.univ_amu.game.lwjgl.LWJGLPlatform;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PlatformTest {
     @Test
     public void test_platform() {
          GraphicPlatform platform = Platform.getGraphicPlatform();
          assertNotNull(platform);
          assertEquals(LWJGLPlatform.class, platform.getClass());
     }
}
