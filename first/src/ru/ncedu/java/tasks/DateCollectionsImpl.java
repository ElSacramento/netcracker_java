package ru.ncedu.java.tasks;

import javax.swing.text.html.HTMLDocument;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static java.util.Collections.sort;

/**
 * Created by Sony on 30.07.2015.
 */
public class DateCollectionsImpl implements DateCollections {
    String date_String = "";
    Date date_Date = new Date();
    DateFormat format = DateFormat.getDateInstance(DateFormat.MEDIUM);
    int format_int = 2;
    Map <String,Element> map_element = new HashMap<>();

    public DateCollectionsImpl() {};
    public DateCollectionsImpl(String date_String){
        this.date_String = date_String;
    }
    public DateCollectionsImpl(Date date_Date) {
        this.date_Date = date_Date;
    }

    @Override
    public void setDateStyle(int dateStyle) {
        switch (dateStyle){
            case 0:
                this.format = DateFormat.getDateInstance(DateFormat.FULL, Locale.US);
                this.format_int = 0;
                break;
            case 1:
                this.format = DateFormat.getDateInstance(DateFormat.LONG, Locale.US);
                this.format_int = 1;
                break;
            case 2:
                this.format = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.US);
                this.format_int = 2;
                break;
            case 3:
                this.format = DateFormat.getDateInstance(DateFormat.SHORT, Locale.US);
                this.format_int = 3;
                break;
            default:
                break;
        }
    }

    @Override
    public Calendar toCalendar(String dateString) throws ParseException {
        Date test_date = this.format.parse(dateString);
        Calendar result = Calendar.getInstance();
        result.setTime(test_date);
        return result;
    }

    @Override
    public String toString(Calendar date) {
        String result = this.format.format(date.getTime());
        return result;
    }

    @Override
    public void initMainMap(int elementsNumber, Calendar firstDate) {
        Random rand = new Random();
        int randomNum = rand.nextInt(2000);
        String key = "";
        Calendar birthDate = firstDate;
        Calendar newbirthdate = Calendar.getInstance();
        newbirthdate.setTime(birthDate.getTime());
        Element new_element = new Element(newbirthdate,randomNum);
        key = toString(newbirthdate);
        this.map_element.put(key,new_element);
        for (int i=1; i < elementsNumber; i++){
            randomNum = rand.nextInt(2000);
            birthDate.add(Calendar.DAY_OF_YEAR, 110);
            newbirthdate = Calendar.getInstance();
            newbirthdate.setTime(birthDate.getTime());
            new_element = new Element(newbirthdate,randomNum);
            key = toString(newbirthdate);
            this.map_element.put(key,new_element);
        }
    }

    @Override
    public void setMainMap(Map<String, Element> map) {
        this.map_element = map;
    }

    @Override
    public Map<String, Element> getMainMap() {
        return this.map_element;
    }

    public Comparator<String> my_comparator = new Comparator<String>() {
        @Override
        public int compare(String s1, String s2) {
            DateFormat sdf = format;
            Date start = null;
            try {
                start = sdf.parse(s1);
            } catch (ParseException e) {
                e.printStackTrace();
                return 1;
            }
            Date end = null;
            try {
                end = sdf.parse(s2);
            } catch (ParseException e) {
                e.printStackTrace();
                return -1;
            }
            if (start.before(end)) return -1;
            else
            if (!(start.before(end))) return 1;
            else return 0;
        }
    };

    public SortedMap<String, Element> getSortedMap() {
        SortedMap<String, Element> result = new TreeMap<String, Element>(my_comparator);
        result.putAll(this.getMainMap());
        return result;
    }

    @Override
    public SortedMap<String, Element> getSortedSubMap() {
        Calendar temp = Calendar.getInstance();
        String today = this.format.format(temp.getTime());
        //SortedMap<String,Element> map_temp = this.getSortedMap();
        SortedMap<String,Element> result = new TreeMap<String, Element>(my_comparator);
        for (Map.Entry<String, Element> entry: map_element.entrySet()){
            if (my_comparator.compare(entry.getKey(), today) == 1)
                result.put(entry.getKey(),entry.getValue());
        }
        //Iterator it = map_temp.entrySet().iterator();
        //for(Map.Entry<String,Element> entry: map_temp.entrySet()) {
          //  if (my_comparator.compare(entry.getKey(), today) == 1)
            //    result.put(entry.getKey(),entry.getValue());
        //}
        //SortedMap<String, Element> result2 = new TreeMap<String, Element>(my_comparator);
        //result2.putAll(result);
        
        return result;
    }

    @Override
    public List<Element> getMainList() {
        List<Element> list_element = new ArrayList<>();
        for (Map.Entry<String,Element> entry: this.getSortedMap().entrySet()) {
            list_element.add(entry.getValue());
        }
        return list_element;
    }

    @Override
    public void sortList(List<Element> list) {
        Comparator<Element> my_comparator_2 = new Comparator<Element>() {
            @Override
            public int compare(Element s1, Element s2){
                Calendar start = s1.getDeathDate();
                Calendar end = s2.getDeathDate();
                if (start.before(end)) return -1;
                else
                    if (!(start.before(end))) return 1;
                    else return 0;
            }
        };
        Set result = new TreeSet<Element>(my_comparator_2);
        result.addAll(list);
        list.clear();
        Iterator<Element> it = result.iterator();
        while(it.hasNext()){
            list.add(it.next());
        }
    }

    @Override
    public void removeFromList(List<Element> list) {
        Iterator<Element> it = list.iterator();
        while(it.hasNext()){
            int temp = it.next().getBirthDate().get(Calendar.MONTH);
            if(temp == 0 || temp == 1 || temp == 11) {
                it.remove();
            }
        }
    }
}
