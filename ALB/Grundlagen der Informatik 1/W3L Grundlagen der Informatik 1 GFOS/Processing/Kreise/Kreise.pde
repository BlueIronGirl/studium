 void setup()
   {
     //Größe der Zeichenfläche
     size(300, 300);
   
     //Gitterraster
     /*for(int x=10; x<300; x=x+10)
      for(int y=10; y<300; y=y+10)
        point (x, y);*/
    frameRate(20);
    smooth();
    //noLoop();
  
  }
  int xy = 290;
  boolean kleiner = true;
  int anzahldurchlaeufe = 0;
  void draw() 
  {
    anzahldurchlaeufe ++;
    //Kreis
    noFill(); //Kreis ist transparent
  
    ellipse(150, 150, xy, xy);
    if (xy > 0 && kleiner) xy = xy - 10;
    if (xy == 0) 
    {
      kleiner = false;
      stroke(int(random(256)),int(random(256)),
         int(random(256)));
      //fill(int(random(256)),int(random(256)),
      //  int(random(256)));
      delay(2000);
    }
    if (xy < 290 && !kleiner) xy = xy + 10;
    if (xy == 290) 
    {
      kleiner = true;
      stroke(int(random(256)),int(random(256)),
         int(random(256)));
      //fill(int(random(256)),int(random(256)),
      //  int(random(256)));
      delay(2000);
    }
    
    if (anzahldurchlaeufe == 100)
        {
          noLoop();
          delay(5000);
          loop();
        }
  }
