package com.emanueltavares.module.exception;

public class PackageNotInstalledException extends Exception {

    private String packageName;

    public PackageNotInstalledException(String packageName) {
        this.packageName = packageName;
    }
    @Override
    public String getMessage() {
        return String.format("%s is not installed!", packageName);
    }

}
