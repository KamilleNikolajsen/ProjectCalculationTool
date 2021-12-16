package projectCalculationTool.subproject;

import projectCalculationTool.project.Project;
import projectCalculationTool.util.exception.SubProjectException;
import projectCalculationTool.util.exception.ValidateException;

public class SubProjectService {
    private SubProjectRepositoryInterface subProjectRepositoryInterface;

    public SubProjectService(SubProjectRepositoryInterface subProjectRepositoryInterface) {
        this.subProjectRepositoryInterface = subProjectRepositoryInterface;
    }

    public void createSubProject(String subProjectName, int projectID, Project project) throws SubProjectException, ValidateException {
        project.setProjectID(projectID);
        SubProject subProject = new SubProject(validateSubProjectName(subProjectName));

        project.addSubproject((subProject));

        subProjectRepositoryInterface.createSubProject(project);
    }

    public SubProject readSubProject(int subProjectID) throws SubProjectException {
        SubProject subProject = subProjectRepositoryInterface.readSubProject(subProjectID);
        return subProject;
    }

    public void updateSubProject(int subprojectID, String subprojectName) throws ValidateException, SubProjectException {
        String name = validateSubProjectName(subprojectName);
        subProjectRepositoryInterface.updateSubProject(subprojectID, name);
    }

    public void deleteSubProject(int subProjectID) throws SubProjectException {
        subProjectRepositoryInterface.deleteSubProject(subProjectID);
    }

    public String validateSubProjectName(String subprojectName) throws ValidateException {
        if (subprojectName != null && !subprojectName.isEmpty() && subprojectName.length() <= 45) {
            return subprojectName;
        } else {
            throw new ValidateException("Subproject name cannot be null or longer than 45 characters.");
        }
    }
}