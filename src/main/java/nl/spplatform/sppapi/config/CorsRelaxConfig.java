//package nl.spplatform.sppapi.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class CorsRelaxConfig implements WebMvcConfigurer {
//
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//            // Allow all origins, all methods, and all headers
//        registry.addMapping("/**")   // Apply to all paths
//                .allowedOrigins("*")  // Allow all origins
//                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH")  // Allow common HTTP methods
//                .allowedHeaders("*")  // Allow all headers
//                .allowCredentials(true);  // Allow credentials (optional)
//        }
//    }
//
