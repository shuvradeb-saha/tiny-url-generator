import { useState } from 'react';
import './App.css';

function App() {
  const [longUrl, setLongUrl] = useState('');
  const [shortUrl, setShortUrl] = useState(undefined);

  const generateShortUrl = async (e) => {
    e.preventDefault();
    console.log("LOng", longUrl);
    if (!longUrl) {
      return;
    }

    const url = 'http://localhost:8080/api/v1/generate'
    const response = await fetch(url, {
      method: "POST", // *GET, POST, PUT, DELETE, etc.
      headers: {
        "Content-Type": "application/x-www-form-urlencoded",
        // 'Content-Type': 'application/x-www-form-urlencoded',
      },
      redirect: "follow", // manual, *follow, error
      referrerPolicy: "no-referrer", // no-referrer, *no-referrer-when-downgrade, origin, origin-when-cross-origin, same-origin, strict-origin, strict-origin-when-cross-origin, unsafe-url
      body: JSON.stringify(longUrl),
    });
    const shortUrl = await response.text();
    console.log('response', shortUrl);
    setShortUrl(shortUrl);
  }

  return (
    <div className="App">
      <header><h1>Tiny Url Generator</h1></header>
      <div className='box'>
        <form onSubmit={generateShortUrl}>
          <label>
            Long Url:{" "}
            <input value={longUrl} onChange={e => {
              e.preventDefault();
              setLongUrl(e.target.value);
            }} />
          </label>
          <button type='submit' >Generate</button>
        </form>
        <div>
          Short Url: <span>{shortUrl}</span>
        </div>
      </div>
    </div>
  );
}

export default App;
