# digital-repository-back

Requisitos
------------------
1. Instalar el MySQLServer y el Workbench (https://dev.mysql.com/downloads/installer/)
2. Instalar SpringBoot en VSCode y descargar las extensiones: Extension Pack For Java, Spring Boot Dashboard, Spring Boot Tools, Spring Initializr Java Support.

Pasos para levantar el back-end:
------------------

1. Abrir el Workbench.
2. Crear un new Schema.                                                                               ![image](https://github.com/project-II-unicauca-2023-2/digital-repository-back/assets/99036946/91eb5934-b14a-45f7-84e2-15d1b2c7c55d)
3. El nombre del Schema debe ser db_gestion_documental y posteriormente dar click en "apply". ![image](https://github.com/project-II-unicauca-2023-2/digital-repository-back/assets/99036946/0d536482-a442-48de-b1f5-0f42bdaddc85)
4. Copiar el script proporcinado en la carpeta del drive y ejecutarlo en un script de Workbench. (https://drive.google.com/drive/u/0/folders/1ryJxp1ZkHrtgrKlKk1pQb9Wm-RgNdQH-).
5. Finalmente, en VSCode ubicados en la carpeta resources, abrir el archivo application.properties y en la propiedad "spring.datasource.password" asignarle la contraseña de root que haya digitado en la instalación de MySQLServer.
