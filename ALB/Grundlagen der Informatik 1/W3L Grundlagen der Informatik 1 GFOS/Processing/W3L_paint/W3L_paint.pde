//Größe der Zeichenfläche
size(300, 300);

//Gitterraster
for(int x=10; x<300; x=x+10)
 for(int y=10; y<300; y=y+10)
   point (x, y);

//Rechteck als Quadrat
noFill(); //Rechteck ist transparent
rect (10,10,280,280); //Rechteck

//Linien für ein W
smooth(); //Mit Anti-Aliasing (Glättung der Treppenstufen)
stroke(255,0,0);//Farbe der folgenden Grafik, hier: rot
strokeWeight(5);//Linienbreite, hier: 5 Pixel
line(30,30,70,270);//Linie von x1, y1 nach x2, y2 
line(70,270,150,150);
line(150,150,230,270);
line(230,270,270,30);

//Halbkreise für eine 3
stroke(0,255,0); //Umstellung der Farbe auf grün
arc(150,95,110,110,-PI/1.5,PI/2); //Kreisbogen
arc(150,205,110,110,-PI/2,PI/1.5);

//Linien für ein L
stroke(0,0,255); //blau
line(80,20,80,280);
line(80,280,220,280);
