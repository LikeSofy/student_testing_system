import os
import sys


def main(param):
    path = sys.argv[1]
    type_file = sys.argv[2]
    print(type_file)
    txt = generate_txt(path, type_file)
    save_to_file(path, type_file, txt)


def generate_txt(path, typefile):
    os.chdir(path)
    txt = ""
    for root, dirs, files in os.walk(".", topdown=False):
        for name in files:
            try:
                if os.path.join(root, name)[-len(typefile):] == typefile:
                    f = open(path + os.path.join(root, name)[1:])
                    txt += "Листинг исходного кода файла " + os.path.join(root, name)[2:] + "\n\n"
                    txt += f.read() + "\n\n"
            except Exception:
                pass

    return txt


def save_to_file(path, file_type, txt):
    f = open(path + "\\generated_source_text" + file_type, "w")
    f.write(txt)
    f.close()


if __name__ == "__main__":
    main(sys.argv)