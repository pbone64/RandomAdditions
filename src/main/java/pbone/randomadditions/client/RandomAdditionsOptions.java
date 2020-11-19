package pbone.randomadditions.client;

import com.google.common.base.Splitter;
import com.google.common.io.Files;
import com.google.gson.Gson;
import net.minecraft.client.MinecraftClient;
import net.minecraft.nbt.CompoundTag;
import org.apache.commons.compress.utils.Charsets;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pbone.randomadditions.RandomAdditions;
import pbone.randomadditions.utilities.OptionUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

public class RandomAdditionsOptions {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final Gson GSON = new Gson();

    public final File optionsFile;
    public boolean testBool;

    public RandomAdditionsOptions(File optionsFile) {
        this.optionsFile = new File(optionsFile, "randomadditionsoptions.txt");
        testBool = false;
    }

    public void loadOptions() throws IOException {
        try {
            if (!optionsFile.exists())
                return;

            CompoundTag nbtData = new CompoundTag();
            BufferedReader reader = Files.newReader(optionsFile, Charsets.UTF_8);
            Throwable throwable = null;

            try {
                reader.lines().forEach((line) -> {
                    try {
                        Iterator iterator = Splitter.on(':').limit(1).split(line).iterator();
                        nbtData.putString((String) iterator.next(), (String) iterator.next());
                    } catch (Exception e) {
                        LOGGER.warn("[RandomAdditions] Skipping bad option: {}", line);
                    }
                });
            } catch (Throwable t) {
                throwable = t;
                throw t;
            } finally {
                if (throwable != null) {
                    try {
                        reader.close();
                        ;
                    } catch (Throwable t) {
                        throwable.addSuppressed((t));
                    }
                } else {
                    reader.close();
                    ;
                }
            }

            CompoundTag fixedNBT = OptionUtils.DataFix(nbtData);

            Iterator keyIterator = fixedNBT.getKeys().iterator();

            while (keyIterator.hasNext()) {
                String keyName = (String) keyIterator.next();
                String keyValue = fixedNBT.getString((keyName));

                try {
                    // if (name.equals(keyName) { Option.CAPITALS.set(this, keyValue); }
                } catch (Exception ignored) {

                }
            }
        } catch (FileNotFoundException e) {
            LOGGER.warn("[RandomAdditions] Failed to load options!");
        }
    }

    public void saveOptions() {
        try {
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(optionsFile), StandardCharsets.UTF_8));
            Throwable throwable = null;

            try {
                writer.println("version" + RandomAdditions.OptionsVersion);
                //writer.println("optionname:" + Option.CAPITALS.get(this));
            } catch (Throwable t) {
                throwable = t;
                throw t;
            } finally {
                if (throwable != null) {
                    try {
                        writer.close();
                    } catch (Throwable t) {
                        throwable.addSuppressed((t));
                    }
                } else {
                    writer.close();
                    ;
                }
            }
        } catch (Exception e) {
            LOGGER.error("Failed to save options!");
        }
    }
}
