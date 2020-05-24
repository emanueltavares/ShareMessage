import os

# script must be in scripts folder
dirpath = os.getcwd()
if os.path.basename(dirpath) != "python":
    print("This script is not located in the python folder!")
    exit()

# going back to parent directory
os.chdir(os.path.pardir)

gradlew_cmd = r'.\gradlew installDebug'
os.system(gradlew_cmd)