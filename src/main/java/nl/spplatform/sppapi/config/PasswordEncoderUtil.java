package nl.spplatform.sppapi.config;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderUtil {
        public static void main(String[] args) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String rawPassword = "user";
            String encodedPassword = encoder.encode(rawPassword);
            System.out.println(encodedPassword);
        }
    }

