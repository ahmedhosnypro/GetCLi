package com.get.businesslayer.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.get.businesslayer.IdType;
import com.get.businesslayer.ItemService;
import com.get.businesslayer.response.Item;
import com.get.businesslayer.response.Resonance;
import com.get.businesslayer.service.IdGenerator;
import com.get.businesslayer.service.IdTypeService;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class Parser {
    // thread-safe hash set
    private final Set<String> uniqueItems = new ConcurrentHashMap<String, Object>().keySet();
    List<String> ids = IdGenerator.generateIds();
    Set<String> newIds = new ConcurrentHashMap<String, Object>().keySet();

    Set<String> idTypes = new ConcurrentHashMap<String, Object>().keySet();

    ItemService itemService = new ItemService();
    IdTypeService idTypeService = new IdTypeService();

    public void parse() {
        for (String id : ids) {
            URL url = buildUrl(id);
            if (url != null) {
                parse(url);
            }
        }
    }

    private void parse(URL url) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            Resonance resonance = mapper.readValue(url, Resonance.class);
            var responseItems = resonance.flatten();
            if (responseItems.isEmpty()) {
                return;
            }

            idTypes.add(new IdType(extractId(url.toString()), "OK"));

            for (var item : responseItems) {
                if (uniqueItems.contains(item.getProd_id())) {
                    continue;
                }
                uniqueItems.add(item.getProd_id());
                parse(item);
            }
        } catch (IOException e) {
            if (!idTypes.contains(extractId(url.toString()))) {
                idTypes.add(extractId(url.toString()));
                idTypeService.save(new IdType(extractId(url.toString()), "IOException"));
            }
        }
    }

    private void parse(Item item) {
        if (!ids.contains(item.getProd_id())) {
            idTypes.add(new IdType(item.getProd_id(), "NewId"));
            newIds.add(item.getProd_id());
            URL url = buildUrl(item.getProd_id());
            if (url != null) {
                parse(url);
            }
        }
        itemService.save(item);
    }

    private URL buildUrl(String id) {
        try {
            return new URL("https://www.res-x.com/ws/r2/Resonance.aspx?appid=CanadianTire13&tk=309459487639379&pg=19012161547560&sg=1&ev=product&ei="
                    + id
                    + "&bx=true&sc=rvproduct1_rr&sc=product1_rr&sc=product2_rr&sc=product5_rr&sc=product6_rr&sc=product4_rr&no=20&storeid=0144&currencycode=CAD&language=en&outofstock=false&inStockStores=0144&availableStores=0144");
        } catch (MalformedURLException e) {
            idTypes.add(new IdType(id, "MalformedURLException"));
            return null;
        }
    }

    private String extractId(String url) {
        String[] parts = url.split("ei=");
        String[] parts2 = parts[1].split("&");
        return parts2[0];
    }
}
