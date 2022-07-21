import os
from sys import exit
import subprocess as sb
from pathlib import PurePosixPath
from time import sleep

class GitExec():
    def __init__(self):
        self.__ssh_agent = "exec ssh-agent bash"
        self.__git_command = ["ssh","-T","git@github.com"]
        self.__add_ssh_key = ["ssh-add.exe"]
        self.__cmd_get_data_repository = ["git","pull","origin","develop"]
        self.__ssh_directory = os.environ["USERPROFILE"] + "\\.ssh"

        self.__actual_git = self.capture_actual_git_repository()


        self.__clean_window = lambda: os.system("clear")
        self.__return_git_repository = lambda: os.chdir(self.__actual_git)
        
    

    def turnon_ssh_server(self):
        print("Ejecutaste el comando: " + "'"+ self.__ssh_agent+ "'"+" [s/n]")
        while True:
            op = input("---> ")

            if op == "s" or op =="S":
                break
            else:
                exit()
        
    
    def connect_to_git(self):
        if self.execute_command(self.__git_command) == 0:
            return True
        return False

    def execute_command(self, command ):
        __nullPool = open(os.devnull, "wb")
        actionCommand = sb.call(command, stdout=__nullPool, stderr= sb.STDOUT)
        return actionCommand

    def capture_actual_git_repository(self):
        return os.getcwd()

    def get_private_key_name(self,current_directory):
        key = [x for x in os.listdir(current_directory) if x.endswith(".pub")]
        return PurePosixPath(key[0]).stem


    def move_privateKey_to_ssh(self, private_key):
        cmd = ["mv",private_key,self.__ssh_directory]
        command = self.execute_command(cmd)
        
        return command

    def add_private_key_to_ssh(self, privatekey):
        self.__add_ssh_key.append(privatekey)
        command = sb.call(self.__add_ssh_key)
        

    def get_data_from_repository(self):
        command = self.execute_command(self.__cmd_get_data_repository)
        if command == 0:
            print("Repositorio Actualizado con exito")
        else:
            print("Hubo un error al traer la data del repositorio")


    def main(self):

        try:
            self.turnon_ssh_server()
            sleep(0.05)
            
            if self.connect_to_git() != True:
                private_key = self.get_private_key_name(self.__actual_git)
                
                if os.path.exists(private_key):
                    if self.move_privateKey_to_ssh(private_key) == 0:
                        print("Se movio la clave privada al directorio ~/.ssh")

                
                os.chdir(self.__ssh_directory)

                self.add_private_key_to_ssh(private_key) 
                    
                
                self.__return_git_repository()

                while True:
                    option = input("Quieres traer la data de la nube[s/n]: ")

                    if option == "s" or option == "S":
                        self.get_data_from_repository()
                        sleep(2)
                        self.__clean_window()
                    elif option == "n" or option == "N":
                        print("Nos vemos!")
                        sleep(2)
                        self.__clean_window()
                        break
                    else:
                        pass

        except Exception as e:
            print(e)
        
        
        


if __name__ == "__main__":
   
    git = GitExec()
    git.main()



    