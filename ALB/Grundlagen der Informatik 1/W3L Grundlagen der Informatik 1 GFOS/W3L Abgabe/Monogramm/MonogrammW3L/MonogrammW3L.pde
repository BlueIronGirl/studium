//Alice Bedow 09.11.2015
size(470,290);
//Punkteraster
for(int x=10;x<width;x=x+10)
  for(int y=10;y<height;y=y+10)
    point(x,y);
//A
fill(0,0,0); //schwarz
quad (160,20 ,185,20, 85 ,270,50 ,250); //links
quad (185,20 ,240,255,225,265,170,25); //rechts
quad (125,165,197.5,150,207,165,120,185); //mitte oben
quad (105,200,215,195,220,214,95,215); //mitte unten
fill(255,255,255); //weiss
quad (103,170,110,177,80,248,70,244); //links weiss

//B
fill(0,0,0); //schwarz
quad (290,20,330,25,310,263,295,260); //links
noFill();
strokeWeight(15);
arc(320,83,130,120,-0.61*PI,0.5*PI); //kreisausschnitt oben
strokeWeight(22);
arc(330,190,130,130,-0.32*PI,0.59*PI); //kreisausschnitt unten innen
strokeWeight(15);
arc(348,193,130,130,-0.39*PI,0.59*PI); //kreisausschnitt unten aussen
strokeWeight(5);
quad (355,170,358,173,330,225,327,225); //oben rechts an kreisausschnitt
fill(0,0,0); //schwarz
quad (350,25,370,40,380,94,360,130); //unten mitte
fill(255,255,255); //weiss
noStroke();
ellipse(400,200,19,19); //kreis unten oben
ellipse(390,230,22,22); //kreis unten mitte
ellipse(365,250,19,19); //kreis unten unten

    