from pathlib import Path

directory = Path("src\\main\\java\\com\\lias\\filters")
directory.mkdir(parents=True, exist_ok=True)   # create the folder if missing

file_names = [
"AuthFilter.java",
"RoleFilter.java"
]

for name in file_names:
    (directory / name).touch(exist_ok=True)    # same as `touch`