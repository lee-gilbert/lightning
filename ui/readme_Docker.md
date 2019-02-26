 
1) Compile and build the ui folders app with:
 
    `ng build --prod`
    
2) Build it into a docker image, from this directory using:

    `docker image build -t tlk-ui .`
    
3) Test the image is there:

    `docker image ls`
    
4) Run the image:    
    `docker run -p 3000:80 --rm tlk-ui`