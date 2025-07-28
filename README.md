🧠 Hafıza Oyunu - Sayı Eşleştirme Macerası! 🎉
Bu proje, Kotlin ve Jetpack Compose kullanarak geliştirilmiş, hafıza tabanlı eğlenceli bir sayı eşleştirme oyunudur. Oyuncular, kartlara dokunarak eşleşen sayıları bulmaya çalışır, bu sayede hem öğrenir hem de eğlenirler!

🚀 Özellikler
  * **Kullanıcı Dostu Arayüz:** Jetpack Compose ile modern ve dinamik bir kullanıcı deneyimi sunar.

  * **İki Zorluk Seviyesi:** "Kolay" (16 kart) ve "Zor" (24 kart) olmak üzere iki farklı zorluk seviyesi mevcuttur.

  * **Temalı Tasarım:** Açık ve koyu tema seçenekleriyle kişiselleştirilebilir bir görünüm sunar.

  * **Skor Takibi:** Oyuncuların önceki skorlarını listeleyerek rekabetçi bir deneyim sağlar.

  * **Süre Kısıtlaması:** Heyecanı artırmak için 60 saniyelik geri sayım süresi bulunur.

  * **Akıcı Animasyonlar:** Kart çevirme ve eşleştirme animasyonlarıyla zenginleştirilmiş görsel deneyim.

  * **MVVM Mimarisi:** Temiz, sürdürülebilir ve test edilebilir kod yapısı için MVVM mimarisi kullanılmıştır.

  * **Yerel Veri Depolama:** Room veritabanı kullanarak skorlar kalıcı olarak kaydedilir.

🛠 Kullanılan Teknolojiler
  * **Kotlin**: Güvenli ve modern Android uygulamaları geliştirmek için tercih edilen programlama dili.

  * **Jetpack Compose**: Deklaratif UI toolkit'i ile hızlı ve sezgisel Android UI geliştirmesi.

  * **MVVM (Model-View-ViewModel)**: Uygulama mimarisi desenidir.

  * **Navigation Component**: Android uygulamalarında ekranlar arası geçişleri yönetmek için.

  * **Room Persistence Library**: SQLite veritabanı üzerinde soyutlama katmanı sağlar.

  * **State Management**: Jetpack Compose'da UI durumunu yönetme prensipleri.

## 📸 Ekran Görüntüleri

| Anasayfa (Açık Tema) | Oyun Kurma (Açık Tema) | Oyun (Açık Tema) | Oyun (Açık Tema) |
| :------------------: | :------------------: | :---------------------: | :---------------------------: |
| <img src="https://github.com/user-attachments/assets/ad4c9eff-e77b-4375-b00d-4e9653c90c3d" alt="Anasayfa Açık Tema" width="200"/> | <img src="https://github.com/user-attachments/assets/cd6004be-5910-4439-b506-ae6e6735f938" alt="Anasayfa Koyu Tema" width="200"/> | <img src="https://github.com/user-attachments/assets/6ac817a6-0ae2-43d9-8559-428877b78385" alt="Oyun Kurma Koyu Tema" width="200"/> | <img src="https://github.com/user-attachments/assets/de3cb68e-a3d9-4296-a1b6-4d75c04c8e32" alt="Oyun Kurma Giriş Koyu Tema" width="200"/> |

| Oyun Başarısız Ekranı (Açık Tema) | Skor Tablosu (Açık Tema) | Ayarlar (Açık Tema) | Anasayfa (Koyu Tema) |
| :------------------------------: | :----------------------------------: | :----------------------------: | :--------------------------------: |
| <img src="https://github.com/user-attachments/assets/277db564-e70b-4e81-8870-55849f3b15fb" alt="Oyun Ekranı Başlangıç Açık Tema" width="200"/> | <img src="https://github.com/user-attachments/assets/26092f28-ee4b-476b-87c5-db405484c7a2" alt="Oyun Ekranı Devam Ediyor Açık Tema" width="200"/> | <img src="https://github.com/user-attachments/assets/de035813-fa84-479c-a5f3-ff365a40814c" alt="Oyun Ekranı Sonrası Açık Tema" width="200"/> | <img src="https://github.com/user-attachments/assets/e14f9623-f1cc-4e65-9b9b-e05d76d82239" alt="Oyun Ekranı Başlangıç Koyu Tema" width="200"/> |

| Oyun Kurma (Koyu Tema) | Oyun (Koyu Tema) | Oyun (Koyu Tema) | Oyun Kazandınız Ekranı (Koyu Tema) |
| :---------------------------------: | :--------------------------------: | :--------------------------: | :-------------------------: |
| <img src="https://github.com/user-attachments/assets/08d7cb62-e9e7-444f-ab39-67cb6f71ff41" alt="Oyun Ekranı Devam Ediyor Koyu Tema" width="200"/> | <img src="https://github.com/user-attachments/assets/d9aa3e72-1565-48c3-91d4-e0a092dfcd5c" alt="Oyun Sonu - Kazandınız Koyu Tema" width="200"/> | <img src="https://github.com/user-attachments/assets/c7de4d01-f7c1-4298-bdda-d41b844399b2" alt="Ayarlar Sayfası Koyu Tema" width="200"/> | <img src="https://github.com/user-attachments/assets/e1cbe3cd-9d4d-4891-8dc2-d1203bf5703f" alt="Ayarlar Sayfası Açık Tema" width="200"/> |

| Ayarlar (Koyu Tema) | Skorlarım Sayfası (Koyu Tema) |
| :---------------------------: | :--------------------------: |
| <img src="https://github.com/user-attachments/assets/dada5116-0869-489c-9b2c-e7b2f3070c0b" alt="Skorlarım Sayfası Koyu Tema" width="200"/> | <img src="https://github.com/user-attachments/assets/6f3db690-78cb-4299-8c24-b97d6ddb6c03" alt="Skorlarım Sayfası Açık Tema" width="200"/> |


⚙️ Uygulama Bölümleri

  * **Başla:** Oyun kurma sayfasına geçiş yapar.

  * **Skorlarım:** Daha önceki skorların listelendiği sayfaya yönlendirir.

  * **Ayarlar:** Tema ve diğer uygulama ayarlarının yapıldığı sayfaya geçiş yapar.

  * **Oyun Kurma Ekranı:** Kullanıcı adı ve zorluk derecesi (Kolay/Zor) seçimi yapılır.

    * Kolay Mod: 16 kart (8 farklı sayı)

    * Zor Mod: 24 kart (12 farklı sayı)

    * Sayılar 1-100 arasında rastgele belirlenir ve her sayıdan 2 adet bulunur.

  * **Oyun Ekranı:** Kartlar rastgele dağıtılır ve başlangıçta kapalıdır. Bir karta tıklandığında animasyonla açılır. Aynı anda en fazla iki kart açık kalır. Sol üstte skor, sağ üstte kalan süre (60 saniyeden geri sayım) gösterilir.

    * Eşleşirse: Kartlar açık kalır.

    * Eşleşmezse: Kısa bir beklemenin ardından kapanır.

  * Oyun Sonu: Tüm kartlar eşleşirse: "Tebrikler! Kazandınız!" mesajı ve skor kaydetme seçeneği sunulur. Süre biterse "Kaybettiniz!" mesajı ve tekrar oynama seçeneği sunulur.

  * **Skorlarım Ekranı:** Daha önceki skorlarınızı listeler. En yüksekten en düşüğe sıralıdır. Sıra numarası, kullanıcı adı, skor bilgisi, süre ve zorluk seviyesi gösterilir.

  * **Ayarlar Ekranı:** "Açık" ve "Koyu" tema arasında geçiş imkanı sunar. Skor listesini sıfırlama seçeneği bulunur.


