/*
 * Copyright 2015 MongoDB, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mycompany.week7_finalexam;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import org.bson.conversions.Bson;

public class FindTest {
    public static void main(String[] args) {
        MongoClient client = new MongoClient();
        MongoDatabase database = client.getDatabase("photos");
        MongoCollection<Document> images = database.getCollection("images");
        long imagesCount = images.count();
        MongoCollection<Document> albums = database.getCollection("albums");

        MongoCursor<Document> cursor = images.find().iterator();
//        List<Document> notFoundImages = new ArrayList<Document>();
        try {
            long count = 0;
            while (cursor.hasNext()) {
                Document cur = cursor.next();
                Bson filter = eq("images", cur.get("_id"));
                List<Document> all = albums.find(filter).into(new ArrayList<Document>());

                if (all.isEmpty()) {
                    images.deleteMany(eq("_id", cur.get("_id")));
                    System.out.println("Image :" + cur.get("_id") + " is deleted");
                }
                count++;
                System.out.println("Processing " + count + " out of " + imagesCount);
            }
        } finally {
            cursor.close();
        }

//        System.out.println("Count:");
//        long count = notFoundImages.size();
//        System.out.println(count);
        System.out.println("Done");

    }
}
