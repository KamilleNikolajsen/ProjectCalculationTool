package projectCalculationTool.project;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;
import projectCalculationTool.employee.Employee;
import projectCalculationTool.util.exception.ProjectException;
import projectCalculationTool.util.exception.ValidateException;

import java.util.ArrayList;

@Controller
public class ProjectController {

    private final ProjectService PROJECT_SERVICE = new ProjectService(new ProjectRepository());

    @GetMapping("/profile")
    // skal den throwe??
    public String profile(WebRequest webRequest, Model model) throws ProjectException {

        Employee employee = (Employee) webRequest.getAttribute("employee", WebRequest.SCOPE_SESSION);

        if (employee != null) {
            ArrayList<Project> projects = PROJECT_SERVICE.readProjects(employee);
            model.addAttribute("employee", employee);

            model.addAttribute("project", projects);
            model.addAttribute("message", webRequest.getParameter("message"));

            return "profile";
        }

        return "redirect:/";
    }

    @PostMapping("/addproject")
    public String createProject(WebRequest webRequest) throws ProjectException, ValidateException {
        String projectName = PROJECT_SERVICE.validateProjectName(webRequest.getParameter("projectname"));

        PROJECT_SERVICE.createProject(projectName,
                (Employee) webRequest.getAttribute("employee", WebRequest.SCOPE_SESSION));

        return "redirect:/profile";
    }

    @GetMapping("/project")
    public String project(WebRequest webRequest, Model model) throws ProjectException {
        int projectID = Integer.parseInt(webRequest.getParameter("id"));
        Employee employee = (Employee) webRequest.getAttribute("employee", WebRequest.SCOPE_SESSION);

        // send hele projektet videre --> ikke hent id i næste controller (evt i webrequest eller session?)
        Project project = PROJECT_SERVICE.readProject(projectID);

        //sæt projekt i session
        webRequest.setAttribute("project", project, WebRequest.SCOPE_SESSION);
        project.setEmployee(employee);

        model.addAttribute("project", project);
        model.addAttribute("message", webRequest.getParameter("message"));

        return "project";
    }

    @GetMapping("/projectoverview")
    public String showOverview(WebRequest webRequest, Model model) throws ProjectException {
        // henter projekt fra session
        Project project = (Project) webRequest.getAttribute("project", WebRequest.SCOPE_SESSION);

        model.addAttribute("project", project);

        return "projectoverview";
    }

    @GetMapping("editProject")
    public String editProject(WebRequest webRequest, Model model) throws ProjectException {

        int projectID = Integer.parseInt(webRequest.getParameter("id"));

        //skal laves om, så vi ikke skal kalde ned til database igen
        Project project = PROJECT_SERVICE.readProject(projectID);

        model.addAttribute("project", project);


        return "editProject";
    }

    @PostMapping("saveProjectChanges")
    public String saveProjectChanges(WebRequest webRequest) throws ProjectException, ValidateException {

        int projectID = Integer.parseInt(webRequest.getParameter("projectID"));

        //skal laves om, så vi ikke skal kalde ned til database igen
        Project project = PROJECT_SERVICE.readProject(projectID);

        String projectName = webRequest.getParameter("projectName");

        PROJECT_SERVICE.updateProject(project, projectName);

        return "redirect:profile?id=" + projectID;
    }

    @GetMapping("deleteProject")
    public String deleteProject(WebRequest webRequest) throws ProjectException {

        int projectID = Integer.parseInt(webRequest.getParameter("id"));

        PROJECT_SERVICE.deleteProject(projectID);

        return "redirect:profile";
    }

    //Referance https://stackoverflow.com/questions/804581/spring-mvc-controller-redirect-to-previous-page
    @ExceptionHandler({ProjectException.class, ValidateException.class})
    public String handleSQLException(Model model, Exception exception, WebRequest webRequest) {
        String referer = webRequest.getHeader("Referer");
        model.addAttribute("message", exception.getMessage());
        return "redirect:" + referer;
    }
}