package Shop;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.File;
public class Management {

    private ArrayList<FurnitureBase> listCards = new ArrayList<>();
    // Добавить описание врезных замков
    public void add(String name, int weight, String color, int width,int length, int cost, int assembled_lengh){
        Sofas cardSofas = new Sofas(getNextID(),name, weight, color, width,length, cost,assembled_lengh);
        listCards.add(cardSofas);
    }
    // Добавить описание навесных замков
    public void add(String name, int weight, String color,int width,int length,int cost,String mattress){
        Beds cardBeds = new Beds(getNextID(),name, weight, color, width, length,cost, mattress);
        listCards.add(cardBeds);
    }
    private int getNextID(){
        int nextID;
        boolean freeID = true;
        do {
            freeID = true;
            nextID = (int)(Math.random() * 100000);
            for (int i = 0; i < listCards.size(); i++) {
                if (listCards.get(i).getId() == nextID)
                    freeID = false;
            }
        }while (freeID == false);
        return nextID;
    }
    public void removeCard(int index){
        if (index >= listCards.size())
            return;
        listCards.remove(index);
    }
    public FurnitureBase getCard(int index){
        if (index >= listCards.size())
            return null;
        return listCards.get(index);
    }
    public FurnitureBase getOf(int index){
        ArrayList<FurnitureBase> listReturn = new ArrayList<FurnitureBase>();
        for (int i = 0; i < listCards.size(); i++){
            FurnitureBase card = listCards.get(i);
            if (card.getId()==index)
                return card;
        }
        return null;
    }
    public FurnitureBase getAll(int row){
        if (listCards.size()>row){
            return listCards.get(row);
        }
        return null;
    }
    public ArrayList<FurnitureBase> findCard(String name){
        ArrayList<FurnitureBase> listReturn = new ArrayList<FurnitureBase>();
        for (int i = 0; i < listCards.size(); i++){
            FurnitureBase card = listCards.get(i);
            if (card.getName().contains(name))
                listReturn.add(card);
        }
        return listReturn;
    }
    public int getCount(){
        return listCards.size();

    }


    public void saveToJson(String fileName) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            objectMapper.writeValue(new File(fileName), listCards);
            System.out.println("Furniture items saved to JSON successfully.");
        } catch (IOException e) {
            System.out.println("Error saving furniture items to JSON: " + e.getMessage());
        }
    }

    public void saveToXml(String fileName) {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // Создание корневого элемента
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("Cards");
            doc.appendChild(rootElement);

            // Создание элементов для каждой карточки и добавление их в корневой элемент
            for (FurnitureBase furniture : listCards) {
                Element cardElement = doc.createElement("Card");
                rootElement.appendChild(cardElement);

                // Создание и добавление элементов для свойств карточки
                Element nameElement = doc.createElement("Name");
                nameElement.appendChild(doc.createTextNode(furniture.getName()));
                cardElement.appendChild(nameElement);

                Element weightElement = doc.createElement("Weight");
                weightElement.appendChild(doc.createTextNode(String.valueOf(furniture.getWeight())));
                cardElement.appendChild(weightElement);

                Element colorElement = doc.createElement("Color");
                colorElement.appendChild(doc.createTextNode(furniture.getColor()));
                cardElement.appendChild(colorElement);

                Element widthElement = doc.createElement("Width");
                widthElement.appendChild(doc.createTextNode(String.valueOf(furniture.getWidth())));
                cardElement.appendChild(widthElement);

                Element lengthElement = doc.createElement("Length");
                lengthElement.appendChild(doc.createTextNode(String.valueOf(furniture.getLength())));
                cardElement.appendChild(lengthElement);

                Element costElement = doc.createElement("Cost");
                costElement.appendChild(doc.createTextNode(String.valueOf(furniture.getCost())));
                cardElement.appendChild(costElement);


                // Добавьте другие элементы свойств карточки по аналогии

                // ...
            }

            // Создание объекта Transformer для записи документа в файл
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(fileName));
            transformer.transform(source, result);

            System.out.println("Furniture items saved to XML successfully.");
        } catch (ParserConfigurationException | TransformerException e) {
            System.out.println("Error saving furniture items to XML: " + e.getMessage());
        }
    }

    public void saveToCsv(String fileName) {
        StringBuilder csv = new StringBuilder();
        csv.append("ID,Name,Weight,Color,Width,Length,Cost\n");

        for (FurnitureBase item : listCards) {
            csv.append(item.getId()).append(",")
                    .append(item.getName()).append(",")
                    .append(item.getWeight()).append(",")
                    .append(item.getColor()).append(",")
                    .append(item.getWidth()).append(",")
                    .append(item.getLength()).append(",")
                    .append(item.getCost()).append("\n");
        }

        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(csv.toString());
            System.out.println("Furniture items saved to CSV successfully.");
        } catch (IOException e) {
            System.out.println("Error saving furniture items to CSV: " + e.getMessage());
        }
    }
}