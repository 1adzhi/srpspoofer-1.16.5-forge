package in.main.srpspoofer;

import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("srpspoofer")
public class SRPSpoofer {
    private static final Logger LOGGER = LogManager.getLogger();

    public SRPSpoofer() {
        LOGGER.info("SRPSpoofer mod initialized.");
    }
}