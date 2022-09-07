import { NavBar } from "../components/Navbar";
import NextLink from "next/link";

export default function Home() {
  return (
    <div>
      <NavBar></NavBar>
      <div className="">
        <div className="flex-col	flex items-center column">
          <div className="my-3 rounded-md bg-teal-100 w-2/3 grid  grid-flow-col gap-0">
            {/* <button className="row-span-1">2</button>
        <button className="row-span-1">1</button> */}
            <div className="row-span-2 col-span-2 ml-3 my-3">
              <NextLink href="">
                <a className="text-xl bold font-mono">Link</a>
              </NextLink>
              <h1>By root</h1>
              <p className="mt-2">
                Lorem ipsum dolor sit, amet consectetur adipisicing elit.
                Voluptate veritatis molestias cupiditate, quia aliquid
                recusandae magni, asperiores vel reprehenderit odio
                voluptatibus, quidem nesciunt praesentium fugit. Odio error
                nesciunt totam facere!
              </p>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}
