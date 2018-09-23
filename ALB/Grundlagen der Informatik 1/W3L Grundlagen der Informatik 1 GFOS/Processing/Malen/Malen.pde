  void setup()
  {
    //Größe der Zeichenfläche
     size(300, 300);
     background(0,0,0); //schwarz
   
     //Gitterraster
     for(int x=10; x<300; x=x+10)
      for(int y=10; y<300; y=y+10)
     { 
       stroke(200,200,80);//dunkelgelb
       point (x, y);
     }
    frameRate(20);
    smooth();
    noFill();
    rect(200,250,40,40);
    strokeWeight(3);
    line(215,270,225,270);//Pluszeichen
    line(220,265,220,275);
    strokeWeight(1);
    rect(250,250,40,40);
    strokeWeight(3);
    line(265,270,275,270); //Minuszeichen
    strokeWeight(3);
    rect(150,250,40,40);
    stroke(255,0,0);
    fill(255,0,0);
    ellipse(170,270,35,35);
    
    
  }
  int linienfarbe[] = {255,120,0};
  int linienbreite = 1;
  void draw() 
  {
    
    //lights();
    stroke(255); //weiß
    strokeWeight(linienbreite);
 
    if(mousePressed) 
    {
      if(mouseY < 250)
      {
        stroke(linienfarbe[0],linienfarbe[1],linienfarbe[2]);
        line(mouseX, mouseY, pmouseX, pmouseY);
        //ellipse(mouseX, mouseY, 1, 1);      
      }

    }
  }
  void mouseClicked()
  {
    if (mouseX > 200 && mouseX < 240 && mouseY > 250 
        && mouseY < 290) 
    {
       linienbreite++;
    }
    if (mouseX > 250 && mouseX < 290 && mouseY > 250 
        && mouseY < 290) 
    {
       linienbreite--;
    }
    if (mouseX > 150 && mouseX < 190 && mouseY > 250 && mouseY < 290)
    {
      linienfarbe[0] = int(random(255));
      linienfarbe[1] = int(random(255));
      linienfarbe[2] = int(random(255)); 
    }
  }
  void keyPressed()
  {
    if(keyCode == UP)
    {
      linienbreite++;
    }
    if(keyCode == DOWN)
    {
      linienbreite--;
    }
    if(keyCode == LEFT)
    {
      linienfarbe[0] = (255+linienfarbe[0]+25)%255; 
      linienfarbe[1] = (255+linienfarbe[0]+25)%255; 
      linienfarbe[2] = (255+linienfarbe[0]+25)%255; 
    }
    if(keyCode == RIGHT)
    {
      linienfarbe[0] = (255+linienfarbe[0]-25)%255; 
      linienfarbe[1] = (255+linienfarbe[0]-25)%255; 
      linienfarbe[2] = (255+linienfarbe[0]-25)%255;   
    }
  }
