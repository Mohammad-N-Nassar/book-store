package com.example.book_store.unit.dao.config;
import com.mongodb.MongoClient;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.MongodConfig;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import java.io.IOException;

public class EmbeddedMongoDB {

	private final MongodExecutable executable;
	private MongoClient client;
	private final Net net;

	public EmbeddedMongoDB() throws IOException {
		MongodStarter starter = MongodStarter.getDefaultInstance();
		this.net = new Net();
		this.executable = starter.prepare(
				MongodConfig.builder()
						.version(Version.Main.DEVELOPMENT)
						.net(net)
						.build()
		);
	}

	public void start() throws IOException {
		this.executable.start();
	}

	public void stop() {
		this.executable.stop();
	}

	public MongoClient getClient() {
		if (this.client != null) {
			return this.client;
		}
		this.client = new MongoClient(this.net.getBindIp(), this.net.getPort());
		return this.client;
	}
}
