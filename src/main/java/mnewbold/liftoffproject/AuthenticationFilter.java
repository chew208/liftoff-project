package mnewbold.liftoffproject;

import mnewbold.liftoffproject.controller.AuthenticationController;
import mnewbold.liftoffproject.models.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import mnewbold.liftoffproject.models.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;


public class AuthenticationFilter extends HandlerInterceptorAdapter {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationController authenticationController;

    private static final List<String> whitelist = Arrays.asList("/", "/login", "/register", "/logout", "/css");


    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws IOException {

        System.out.println("preHandle\nrequestURI: " + request.getRequestURI() + "\nwhitelist: " + whitelist.toString() + "\n");

        HttpSession session = request.getSession();
        User user = authenticationController.getUserFromSession(session);


        // (u The user is logged in
        if (user != null) {
            System.out.println("User " + user.getUsername() + " is logged in.");
            return true;
        }

        // The user is NOT logged in
        if (!isWhitelisted(request.getRequestURI())) {
            response.sendRedirect("/login");
            return false;
        }
        return true;

//        if (isWhitelisted(request.getRequestURI())) {
//            return true;
//        }
//
//        HttpSession session = request.getSession();
//        User user = authenticationController.getUserFromSession(session);
//
//        if (user != null) {
//            return true;
//        }
//
//        response.sendRedirect("/login");
//        return false;
    }

    private static boolean isWhitelisted(String path) {
        String pathSlash = path, pathRootSlash;
        if (!path.endsWith("/")) pathSlash = path.concat("/");
        System.out.println("isWhiteListed\npath: " + path + "\nwhitelist: " + whitelist.toString() + "\n");
        for (String pathRoot : whitelist) {
            pathRootSlash = pathRoot;
            if (!pathRoot.endsWith("/"))
                pathRootSlash = pathRoot.concat("/");
            System.out.println("path: " + path + ", " + pathSlash + "\npathRoot: " + pathRoot + ", " + pathRootSlash);
            if (path.equals(pathRoot) ||
                    pathSlash.equals(pathRootSlash) ||
                    (!pathRootSlash.equals("/") && (
                            (!path.endsWith("/") && pathSlash.startsWith(pathRootSlash))))) {
                System.out.println("whitelisted\n");
                return true;
            }
        }
        return false;
    }
}
