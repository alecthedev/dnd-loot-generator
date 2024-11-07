package edu.bsu.cs;

import net.minidev.json.JSONArray;

import java.io.IOException;
import java.util.*;

public class ItemBuilder {

    MagicItemsParser attributeParser = new MagicItemsParser();
    WeaponItemParser weaponItemParser = new WeaponItemParser();
    ArmorItemParser armorItemParser = new ArmorItemParser();

    protected List<Item> generateAmountOfItems() throws IOException {
        List<Item> itemsList = new ArrayList<>();
        for (int i = 0; i < Configuration.getNumItemsRequested(); i++) {
            itemsList.add(generateAny());
        }
        return itemsList;
    }

    private Item generateAny() throws IOException {
        return switch (new Random().nextInt(3)) {
            case 0 -> generateWeapon("src/main/resources/weapons.txt");
            case 1 -> generateArmor("src/main/resources/armor.txt");
            case 2 -> generateMagicItem("src/main/resources/magicitems.txt");
            default -> throw new IllegalStateException("Unexpected value: " + 3);
        };
    }

    protected Item generateWeapon(String filePath) throws IOException {
        JSONArray nameJsonArray =  weaponItemParser.parseStandardItemName(JsonFileReader.readFileToString(filePath));
        int randomIndex = selectRandomItemIndex(nameJsonArray);
        Dictionary<Integer, String> statDictionary = weaponItemParser.parseAllWeaponStats(JsonFileReader.readFileToString(filePath), randomIndex);
        Item item = new Item(nameJsonArray.get(randomIndex).toString(), OutputFormatter.formatWeaponStats(statDictionary));
        item.setType("Weapon");
        return item;
    }

    protected Item generateArmor(String filePath) throws IOException {
        JSONArray nameJsonArray =  weaponItemParser.parseStandardItemName(JsonFileReader.readFileToString(filePath));
        int randomIndex = selectRandomItemIndex(nameJsonArray);
        Dictionary<Integer, String> statDictionary = armorItemParser.parseAllArmorStats(JsonFileReader.readFileToString(filePath), randomIndex);
        Item item = new Item(nameJsonArray.get(randomIndex).toString(), OutputFormatter.formatArmorStats(statDictionary));
        item.setType("Armor");
        return item;
    }

    protected Item generateMagicItem(String filePath) throws IOException {
        String fileContents = JsonFileReader.readFileToString(filePath);
        String[] pageLines = fileContents.split("\n");
        int pageIndex = new Random().nextInt(pageLines.length);

        JSONArray nameJsonArray =  attributeParser.parseMagicItemName(fileContents, pageIndex);
        JSONArray rarityJsonArray =  attributeParser.parseMagicItemRarity(fileContents, pageIndex);
        JSONArray typeJsonArray =  attributeParser.parseMagicItemType(fileContents, pageIndex);
        JSONArray attunementJsonArray =  attributeParser.parseMagicItemAttunement(fileContents, pageIndex);
        JSONArray descriptionJsonArray = attributeParser.parseMagicItemDescription(fileContents,pageIndex);

        int selectedIndex = selectRandomItemIndex(nameJsonArray);

        return new Item(
                nameJsonArray.get(selectedIndex).toString(),
                OutputFormatter.formatRarity(rarityJsonArray.get(selectedIndex).toString()),
                typeJsonArray.get(selectedIndex).toString(),
                OutputFormatter.formatAttunement(attunementJsonArray.get(selectedIndex).toString()),
                descriptionJsonArray.get(selectedIndex).toString()
                );
    }

    protected static int selectRandomItemIndex(JSONArray array){
        return new Random().nextInt(array.size());
    }

}
