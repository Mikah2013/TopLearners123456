package com.example.toplearners.model;

public class Project {

    private String firstName;
    private String lastName;
    private String email;
    private String linkToProject;

    public Project() {
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getLinkToProject() {
        return linkToProject;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setLinkToProject(String linkToProject) {
        this.linkToProject = linkToProject;
    }
}
