package fr.kage.nexus3;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Properties;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Nexus3ExportApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Nexus3ExportApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		if (args.length > 0) {
			if (args.length >= 2) {
				String url = args[0];
				String repoId = args[1];
				String downloadPath = args.length > 2 ? args[2] : null;
				String groupId = args.length > 3 ? args[3] : null;
				String name = args.length > 4 ? args[4] : null;
				String version = args.length > 5 ? args[5] : null;

				Properties credentials = new Properties();
				credentials.setProperty("authenticate", "false");
				boolean authenticate = false;
				String username = "admin";
				String password = "admin";
				new DownloadRepository(url, repoId, groupId, name, version, downloadPath, authenticate, username, password).start();
				return;
			}
			else
				System.out.println("Missing arguments for download.");
		}
		else
			System.out.println("No specified argument.");

		System.out.println("Usage:");
		System.out.println("\tnexus3 http://url.to/nexus3 repositoryId [localPath]");
		System.exit(1);
	}

	private Properties loadCredentials() {
		Properties properties = new Properties();

		File credentialsFile = new File(Paths.get("").toAbsolutePath().toFile(), "credentials.properties");

		if (!credentialsFile.exists()) {
			return properties;
		}

		try {
		properties.load(new FileInputStream(credentialsFile));
		} catch(IOException e) {
			System.err.println("Credentials file " + credentialsFile.getAbsolutePath() + " could not be found or is malformed!");
			System.exit(1);
		}

		return properties;
	}

	private String removeTrailingQuotes(String value) {
		String result; 
		if ((value.startsWith("\"") && value.endsWith("\"")) || (value.startsWith("\'") && value.endsWith("\'"))) {
			result = value.substring(1, value.length() - 1);
		} else {
			result = value;
		}

		return result;
	}
}
