package grondag.hamsandwich;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.fabricmc.api.ClientModInitializer;

public class HamSandwich implements ClientModInitializer {
	public static final String MOD_ID = "ham_sandwich";
	public static Logger LOG = LogManager.getLogger("Ham Sandwich");

	private static AtomicInteger NEXT_ID = new AtomicInteger();

	public static final Executor EXECUTOR =  Executors.newFixedThreadPool(Math.max(Runtime.getRuntime().availableProcessors() - 1, 1), (r) -> {
		final Thread workerThread = new Thread(r);
		workerThread.setName("Render-Worker-" + NEXT_ID.getAndIncrement());
		workerThread.setDaemon(true);
		return workerThread;
	});

	@Override
	public void onInitializeClient() {
		EXECUTOR.hashCode();
	}
}
